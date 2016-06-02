<h1><a href="{{ url('tasks') }}">To-do App</a></h1>
<h2>{{ $task->name }}</h2>
<p>
    생성일: {{ $task->created_at }}
</p>
<form action="{{ url('tasks/' . $task->id) }}" method="post">
    {{ csrf_field() }}
    {{ method_field('PUT') }}
    <input type="text" name="name" value="{{ $task->name }}">
    <button type="submit">수정</button>
</form>
<p>
    <a href="{{ url('tasks') }}">목록</a>
</p>
