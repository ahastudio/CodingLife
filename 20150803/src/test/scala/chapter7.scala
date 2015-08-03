package parallelism

import org.scalatest._
import java.util.concurrent._
import java.util.concurrent.atomic._
import akka.actor.Actor

sealed trait Future[A] {
  private[parallelism] def apply(k: A => Unit): Unit
}

object Par {
  type Par[A] = ExecutorService => Future[A]

  def unit[A](a: A): Par[A] =
    (es: ExecutorService) => new Future[A] {
      def apply(cb: A => Unit): Unit =
        cb(a)
    }

  def fork[A](a: => Par[A]): Par[A] =
    es => new Future[A] {
      def apply(cb : A => Unit): Unit =
        eval(es)(a(es)(cb))
    }

  def eval(es: ExecutorService)(r: => Unit): Unit =
    es.submit(new Callable[Unit] { def call = r })

  def lazyUnit[A](a: => A): Par[A] = fork(unit(a))

  def map2[A, B, C](p: Par[A], p2: Par[B])(f: (A, B) => C): Par[C] =
    es => new Future[C] {
      def apply(cb: C => Unit): Unit = {
        var ar: Option[A] = None
        var br: Option[B] = None

        val combiner = new Actor {
          def receive = {
            case Left(a: A) => br match {
              case None => ar = Some(a)
              case Some(b) => eval(es)(cb(f(a, b)))
            }
            case Right(b: B) => ar match {
              case None => br = Some(b)
              case Some(a) => eval(es)(cb(f(a, b)))
            }
          }
        }

        p(es)(a => combiner.sender ! Left(a))
        p2(es)(b => combiner.sender ! Right(b))
      }
    }

  def run[A](es: ExecutorService)(p: Par[A]): A = {
    val ref = new AtomicReference[A]
    val latch = new CountDownLatch(1)
    p(es) { a => ref.set(a); latch.countDown }
    latch.await
    ref.get
  }

  def equal[A](e: ExecutorService)(p: Par[A], p2: Par[A]): Boolean =
    run(e)(p) == run(e)(p2)
}

class TestChapter7 extends FlatSpec with Matchers {
  val es: ExecutorService = Executors.newCachedThreadPool

  it should "check many executor" in {
    val a = Par.lazyUnit(42 + 1)
    // FAIL - val es: ExecutorService = Executors.newSingleThreadExecutor
    // FAIL - val es: ExecutorService = Executors.newFixedThreadPool(1)
    val es: ExecutorService = Executors.newFixedThreadPool(2)
    Par.equal(es)(a, Par.fork(a)) should be (true)
  }

  it should "check single thread" in {
    val a = Par.lazyUnit(42 + 1)
    val es: ExecutorService = Executors.newSingleThreadExecutor
    Par.run(es)(a) should be (42 + 1)
  }

  it should "check fixed thread pool" in {
    val a = Par.lazyUnit(42 + 1)
    val es: ExecutorService = Executors.newFixedThreadPool(1)
    Par.run(es)(a) should be (42 + 1)
  }
}
