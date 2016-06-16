<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

use App\Http\Requests;

use App\Task;

class TaskController extends Controller
{
    function index()
    {
        $tasks = Task::orderBy('created_at', 'desc')->get();
        return view('task.index', [
            'tasks' => $tasks
        ]);
    }

    function show($id)
    {
        $task = Task::find($id);
        return view('task.show', [
            'task' => $task
        ]);
    }

    function store(Request $request)
    {
        $this->validate($request, [
            'name' => 'required'
        ]);
        $task = new Task;
        $task->name = $request->name;
        $task->save();
        return redirect('/tasks');
    }

    function update($id, Request $request)
    {
        $this->validate($request, [
            'name' => 'required'
        ]);
        $task = Task::find($id);
        $task->name = $request->name;
        $task->save();
        return redirect('tasks/' . $task->id);
    }

    function destroy($id)
    {
        $task = Task::find($id);
        $task->delete();
        return redirect('/tasks');
    }
}
