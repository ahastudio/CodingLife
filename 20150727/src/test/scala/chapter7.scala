import org.scalatest._
import java.util.concurrent._

object Par {
  type Par[A] = ExecutorService => Future[A]

  def unit[A](a: A): Par[A] = (es: ExecutorService) => UnitFuture(a)

  private case class UnitFuture[A](get: A) extends Future[A] {
    def isDone = true
    def get(timeout: Long, units: TimeUnit) = get
    def isCancelled = false
    def cancel(evenIfRunning: Boolean): Boolean = false
  }

  def map2[A, B, C](a: Par[A], b: Par[B])(f: (A, B) => C): Par[C] =
    (es: ExecutorService) => {
      val af = a(es)
      val bf = b(es)
      UnitFuture(f(af.get, bf.get))
    }

  def fork[A](a: => Par[A]): Par[A] =
    es => es.submit(new Callable[A] {
      def call = a(es).get
    })

  def lazyUnit[A](a: => A): Par[A] = fork(unit(a))

  def asyncF[A, B](f: A => B): A => Par[B] =
    (a: A) => lazyUnit(f(a))

  def sequence[A](ps: List[Par[A]]): Par[List[A]] = ps match {
    case Nil => unit(Nil)
    case h :: t => map2(h, sequence(t))((a, b) => a :: b)
  }

  def sequence2[A](ps: List[Par[A]]): Par[List[A]] =
    ps.foldRight(unit(List.empty[A]))((a, b) => map2(a, b)(_ :: _))

  def parMap[A,B](ps: List[A])(f: A => B): Par[List[B]] = fork {
    val fbs: List[Par[B]] = ps.map(asyncF(f))
    sequence(fbs)
  }

  def parFilter[A](as: List[A])(f: A => Boolean): Par[List[A]] =
    map2(parMap(as)(a => (a, f(a))), unit(())) { (as, _) =>
      as.foldRight(List.empty[A]) {
        case ((h, true), t) => h :: t
        case ((h, false), t) => t
      }
    }
}

class TestChapter7 extends FlatSpec with Matchers {
  val es: ExecutorService = Executors.newCachedThreadPool

  // 연습문제 7.1
  it should "map Pars" in {
    Par.map2(Par.unit(1), Par.unit(2))(_ + _)(es).get should be (3)
  }

  // 연습문제 7.4
  it should "evaluate its result asynchoronously" in {
    Par.asyncF[Int, Int](a => a * 2)(3)(es).get should be (6)
  }

  // 연습문제 7.5
  it should "change the Par list" in {
    Par.sequence(List(Par.unit(1), Par.unit(2)))(es).get should be (List(1, 2))
    Par.sequence2(List(Par.unit(1), Par.unit(2)))(es).get should be (List(1, 2))
  }

  // 연습문제 7.6
  it should "filter the list" in {
    Par.parFilter(List(1, 2, 3, 4))(_ % 2 == 0)(es).get should be (List(2, 4))
  }
}
