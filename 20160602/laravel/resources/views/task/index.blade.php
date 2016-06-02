<h1><a href="{{ url('tasks') }}">To-do list</a></h1>
<form action="{{ url('tasks') }}" method="post">
    {{ csrf_field() }}
    <input type="text" name="name">
    <button type="submit">추가</button>
</form>
@if (count($tasks) > 0)
    @foreach ($tasks as $task)
        <p>
            <a href="{{ url('tasks/' . $task->id) }}">{{ $task->name }}</a>
            <form action="{{ url('tasks/' . $task->id) }}" method="post">
                {{ csrf_field() }}
                {{ method_field('DELETE') }}
                <button type="submit">삭제</button>
            </form>
        </p>
    @endforeach
@else
    <p>할 일을 등록해 주세요</p>
@endif
