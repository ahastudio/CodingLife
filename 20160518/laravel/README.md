# Laravel 5.2 Sample

## Composer

https://getcomposer.org/

```
$ brew update && brew install composer
```

## Laravel 5.2

https://laravel.com/docs/5.2

```
$ composer global require "laravel/installer"
```

## 어떻게 만들었나?

```
$ export PATH=~/.composer/vendor/bin:$PATH
$ laravel new laravel-sample
$ mv laravel-sample laravel
$ cd laravel
$ cat .env
```

## 서버 실행

```
$ php artisan serve
```

또는

```
$ cd public
$ php -S localhost:8000
```
