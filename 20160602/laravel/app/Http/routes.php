<?php

use App\Task;
use Illuminate\Http\Request;

/*
|--------------------------------------------------------------------------
| Application Routes
|--------------------------------------------------------------------------
|
| Here is where you can register all of the routes for an application.
| It's a breeze. Simply tell Laravel the URIs it should respond to
| and give it the controller to call when that URI is requested.
|
*/

Route::get('tasks', function () {
    $tasks = Task::orderBy('created_at', 'desc')->get();
    return view('task.index', [
        'tasks' => $tasks
    ]);
});

Route::get('tasks/{task}', function (Task $task) {
    return view('task.show', [
        'task' => $task
    ]);
});

Route::post('tasks', function (Request $request) {
    // TODO: Validation 필요함.
    $task = new Task;
    $task->name = $request->name;
    $task->save();
    return redirect('/tasks');
});

Route::put('tasks/{task}', function (Task $task, Request $request) {
    // TODO: Validation 필요함.
    $task->name = $request->name;
    $task->save();
    return redirect('tasks/' . $task->id);
});

Route::delete('tasks/{task}', function (Task $task) {
    $task->delete();
    return redirect('/tasks');
});
