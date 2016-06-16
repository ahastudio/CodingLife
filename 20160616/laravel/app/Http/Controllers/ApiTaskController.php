<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

use App\Http\Requests;

use App\Task;

class ApiTaskController extends Controller
{
    function index()
    {
        $tasks = Task::orderBy('created_at', 'desc')->get();
        return json_encode([
            'page' => 1,
            'tasks' => $tasks
        ]);
    }
}
