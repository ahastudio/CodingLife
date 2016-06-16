<html>
    <head>
        <meta charset="utf-8">
        <title>To-do App</title>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/vue/1.0.24/vue.min.js"></script>
    </head>
    <body>
        <h1>To-do App</h1>
        <div id="container">
            <div id="clock">
                <span v-on:click="toggle()">ㅇ</span>
                <span v-if="!hidden">
                    {{hours}}:{{minutes}}:{{seconds}}
                </span>
                <hr>
            </div>
            <div id="tasks">
                <div v-if="!currentTask">
                    <input v-model="name" type="text" placeholder="Task">
                    <button v-on:click="addTask()">Add Task</button>
                    <p>{{ name }}</p>
                    <ul>
                        <li v-for="task in tasks" v-on:click="showTask(task)">
                            {{ task.name }}
                        </li>
                    </ul>
                </div>
                <div v-if="currentTask">
                    <button v-on:click="showList()">List</button>
                    <hr>
                    {{ currentTask.id }}
                    {{ currentTask.name }}
                    {{ currentTask.created_at }}
                </div>
            </div>
        </div>
        <script>
            var clockVM = new Vue({
                el: '#clock',
                data: {
                    hours: '',
                    minutes: '',
                    seconds: '',
                    hidden: false
                },
                methods: {
                    update: function() {
                        var now = new Date();
                        this.hours = now.getHours();
                        this.minutes = now.getMinutes();
                        this.seconds = now.getSeconds();
                    },
                    toggle: function() {
                        this.hidden = !this.hidden;
                    }
                }
            });

            setInterval(function() {
                clockVM.update();
            }, 1000);
            clockVM.update();

            var tasksVM = new Vue({
                el: '#tasks',
                data: {
                    tasks: [],
                    name: '',
                    currentTask: null
                },
                methods: {
                    load: function(page) {
                        $.getJSON('/api/v1/tasks', { page: page })
                        .done(data => {
                            this.tasks = data.tasks;
                        });
                    },
                    addTask: function() {
                        if (this.name) {
                            this.tasks.push({
                                name: this.name
                            });
                        }
                    },
                    showTask: function(task) {
                        this.currentTask = task;
                    },
                    showList: function() {
                        this.currentTask = null;
                    }
                }
            });

            tasksVM.load();
        </script>
    </body>
</html>
