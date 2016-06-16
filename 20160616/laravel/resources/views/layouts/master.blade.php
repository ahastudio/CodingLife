<html>
    <head>
        <meta charset="utf-8">
        <title>@yield('title')</title>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
        @yield('javascripts')
    </head>
    <body>
        <h1><a href="{{ url('tasks') }}">To-do App</a></h1>
        <div id="container">
            @yield('content')
        </div>
    </body>
</html>
