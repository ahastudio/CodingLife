# Issue Tracker Prototype

- <https://guides.rubyonrails.org/v7.0.1/getting_started.html>
- <https://guides.rubyonrails.org/v7.0.1/routing.html>
- <https://guides.rubyonrails.org/v7.0.1/active_record_validations.html>

## 일단 실행

```bash
bunlde

bin/rails server
```

<http://localhost:3000/>

## Home page

```bash
bin/rails generate controller Welcome index --skip-routes --skip-helper
```

```bash
vi config/routes.rb
```

```rb
Rails.application.routes.draw do
  root 'welcome#index'
end
```

```bash
vi app/views/welcome/index.html.erb
```

```erb
<h1>Welcome</h1>
<p>Hello, world!</p>
```

## Project, Task, Job 생성

```bash
rails generate model Project title:string body:text

rails generate model Task \
  project:references sequence:integer \
  title:string body:text

rails generate model Job \
  task:references sequence:integer \
  title:string body:text
```

`db/migrate` 폴더에 추가된 Migration 파일의 이름 및 내용은 적절히 수정함.

DB 마이그레이션 먼저 진행.

```bash
bin/rails db:migrate
```

`app/models/project.rb` 파일 수정.

```rb
class Project < ApplicationRecord
  has_many :tasks
  has_many :jobs, through: :tasks
end
```

`app/models/task.rb` 파일 수정.

```rb
class Task < ApplicationRecord
  belongs_to :project
  has_many :jobs
end
```

## 데이터 준비

```bash
bin/rails console
```

```rb
project = Project.create!(title: 'Test', body: 'This is test proejct')

project.tasks.create!(title: 'Task #1', body: 'number 1')
project.tasks.create!(sequence: 2, title: 'Task #2', body: 'number 2')

project.tasks.first.jobs.create!(title: 'Job #1', body: 'for me')
project.tasks.first.jobs.create!(title: 'Job #2', body: 'for you')
```

## Job 목록 보여주기

`app/controllers/welcome_controller.rb`

```rb
class WelcomeController < ApplicationController
  def index
    @project = Project.first
    @jobs = @project.jobs
  end
end
```

`app/views/welcome/index.html.erb`

```erb
<h1>Project: <%= @project.title %></h1>

<h2>Jobs</h2>
<ul>
  <% @jobs.each do |job| %>
    <li>
      <%= job.title %>
    </li>
  <% end %>
</ul>
```

## Job 페이지

```bash
bin/rails generate controller Jobs index show \
  --skip-routes --skip-helper --skip-collision-check
```

`config/routes.rb`

```rb
Rails.application.routes.draw do
  root 'welcome#index'

  resources :jobs
end
```

`app/controllers/jobs_controller.rb`

```rb
class JobsController < ApplicationController
  def index
    @jobs = Job.all
  end

  def show
    @job = Job.find(params[:id])
  end
end
```

`app/views/jobs/index.html.erb`

```erb
<h1>Jobs</h1>
<ul>
  <% @jobs.each do |job| %>
    <li>
      <%= link_to job.title, job %>
      (<%= job.task.title %>)
    </li>
  <% end %>
</ul>
```

`app/views/jobs/show.html.erb`

```erb
<h1>Job</h1>
<dl>
  <dt>Title:</dt>
  <dd><%= @job.title %></dd>
  <dt>Body:</dt>
  <dd><%= simple_format @job.body %></dd>
</dl>
```

## Home과 Job 페이지 연결하기

`app/views/layouts/application.html.erb`

```erb
<!DOCTYPE html>
<html>
  <head>
    <title>Tickets Demo</title>
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <%= csrf_meta_tags %>
    <%= csp_meta_tag %>
    <%= stylesheet_link_tag "application", "data-turbo-track": "reload" %>
    <%= javascript_importmap_tags %>
  </head>
  <body>
    <header>
      <nav>
        <%= link_to 'Home', root_url %>
        |
        <%= link_to 'Jobs', jobs_url %>
      </nav>
    </header>
    <main>
      <%= yield %>
    </main>
  </body>
</html>
```

`app/views/welcome/index.html.erb`

```erb
<h1>Project: <%= @project.title %></h1>

<h2>Jobs</h2>
<ul>
  <% @jobs.each do |job| %>
    <li>
      <%= link_to job.title, job %>
      (<%= job.task.title %>)
    </li>
  <% end %>
</ul>
```

## Ticket 생성

```bash
rails g model Ticket \
  project:references body:text priority:integer assignee:string \
  target:references{polymorphic}
```

`target`를 지정하지 않을 수 있도록 마이그레이션 파일 수정.

```rb
class CreateTickets < ActiveRecord::Migration[7.0]
  def up
    create_table :tickets do |t|
      t.references :project, null: false, foreign_key: true
      t.text :body
      t.integer :priority, null: false, default: 5
      t.string :assignee
      t.references :target, polymorphic: true

      t.timestamps
    end

    change_column_null :tickets, :target_type, true
    change_column_null :tickets, :target_id, true
  end

  def down
    drop_table :tickets
  end
end
```

`app/models/ticket.rb`

```rb
class Ticket < ApplicationRecord
  belongs_to :project
  belongs_to :target, polymorphic: true, optional: true

  validates :body, presence: true
end
```

`app/models/project.rb`

```rb
class Project < ApplicationRecord
  has_many :tasks
  has_many :jobs, through: :tasks
  has_many :tickets
end
```

```bash
bin/rails db:migrate
```

적당히 티켓 데이터 추가.

```bash
bin/rails console
```

```rb
project = Project.first

project.tickets.create!(body: 'Fist ticket!')
project.tickets.create!(body: 'What is this?', target: Task.first)
project.tickets.create!(body: 'Hmm...', target: Job.first)
```

## Ticket 페이지

```bash
bin/rails generate controller Tickets index show \
  --skip-routes --skip-helper --skip-collision-check
```

`config/routes.rb`

```rb
Rails.application.routes.draw do
  root 'welcome#index'

  resources :jobs
  resources :tickets
end
```

`app/controllers/tickets_controller.rb`

```rb
class TicketsController < ApplicationController
  def index
    @tickets = Ticket.all
  end

  def show
    @ticket = Ticket.find(params[:id])
  end
end
```

`app/views/tickets/index.html.erb`

```erb
<h1>Tickets</h1>
<ul>
  <% @tickets.each do |ticket| %>
    <li>
      <%= link_to ticket do %>
        <%= ticket.body %>
        <% if ticket.target.present? %>
          (<%= ticket.target_type %>[<%= ticket.target_id %>])
        <% end %>
      <% end %>
    </li>
  <% end %>
</ul>
```

`app/views/tickets/show.html.erb`

```erb
<h1>Ticket</h1>
<dl>
  <dt>Body:</dt>
  <dd><%= simple_format @ticket.body %></dd>
  <dt>Created at:</dt>
  <dd><%= @ticket.created_at.strftime('%Y-%m-%d %H:%M:%S') %></dd>
  <dt>Priority:</dt>
  <dd><%= @ticket.priority %></dd>
  <dt>Assignee:</dt>
  <dd><%= @ticket.assignee %></dd>
</dl>
```

## Home과 Ticket 페이지 연결하기

`app/controllers/welcome_controller.rb`

```rb
class WelcomeController < ApplicationController
  def index
    @project = Project.first
    @jobs = @project.jobs
    @tickets = @project.tickets
  end
end
```

`app/views/welcome/index.html.erb`

```erb
<h1>Project: <%= @project.title %></h1>

<h2>Jobs</h2>
<ul>
  <% @jobs.each do |job| %>
    <li>
      <%= link_to job.title, job %>
      (<%= job.task.title %>)
    </li>
  <% end %>
</ul>

<h2>Tickets</h2>
<ul>
  <% @tickets.each do |ticket| %>
    <li>
      <%= link_to ticket do %>
        <%= ticket.body %>
        <% if ticket.target.present? %>
          (<%= ticket.target_type %>[<%= ticket.target_id %>])
        <% end %>
      <% end %>
    </li>
  <% end %>
</ul>
```

`app/views/layouts/application.html.erb`

```erb
<!DOCTYPE html>
<html>
  <head>
    <title>Tickets Demo</title>
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <%= csrf_meta_tags %>
    <%= csp_meta_tag %>
    <%= stylesheet_link_tag "application", "data-turbo-track": "reload" %>
    <%= javascript_importmap_tags %>
  </head>
  <body>
    <header>
      <nav>
        <%= link_to 'Home', root_url %>
        |
        <%= link_to 'Jobs', jobs_url %>
        |
        <%= link_to 'Tickets', tickets_url %>
      </nav>
    </header>
    <main>
      <%= yield %>
    </main>
  </body>
</html>
```

## Frame 생성

```bash
rails generate model Frame \
  task:references image_url:string width:integer height:integer \
  job:references
```

`app/models/frame.rb`

```rb
class Frame < ApplicationRecord
  belongs_to :task
  belongs_to :job, optional: true
end
```

`app/models/task.rb`

```rb
class Task < ApplicationRecord
  belongs_to :project
  has_many :frames
  has_many :jobs
end
```

`app/models/job.rb`

```rb
class Job < ApplicationRecord
  belongs_to :task
  has_many :frames
end
```

```bash
bin/rails console
```

```rb
require 'net/http'

project = Project.first
task = project.tasks.first

10.times do |index|
  res = Net::HTTP.get_response(URI('https://picsum.photos/800/600'))
  image_url = res['location']
  task.frames.create!(
    image_url: image_url, width: 800, height: 600,
    job: index < 5 ? task.jobs.first : nil
  )
end
```

## Ticket 연결

`app/models/job.rb`

```rb
class Job < ApplicationRecord
  belongs_to :task
  has_many :frames
  has_many :tickets, as: :target
end
```

`app/models/frame.rb`

```rb
class Frame < ApplicationRecord
  belongs_to :task
  belongs_to :job, optional: true
  has_many :tickets, as: :target
end
```

## Frame 페이지

```bash
bin/rails generate controller Frames show --skip-routes --skip-helper
```

`config/routes.rb`

```rb
Rails.application.routes.draw do
  root 'welcome#index'

  resources :jobs do
    resources :frames
  end

  resources :tickets
end
```

`app/controllers/frames_controller.rb`

```rb
class FramesController < ApplicationController
  def show
    @job = Job.find(params[:job_id])
    @frame = @job.frames.find(params[:id])
  end
end
```

`app/views/frames/show.html.erb`

```erb
<h1>Job: <%= @job.title %></h1>

<%= image_tag(@frame.image_url, alt: '') %>

<h2>Tickets</h2>
<ul>
  <% @frame.tickets.each do |ticket| %>
    <li><%= link_to ticket.body, ticket %></li>
  <% end %>
</ul>

<h2>Frames</h2>
<div>
  <% @job.frames.each_with_index do |frame, index| %>
    <%= link_to image_tag(frame.image_url, height: 100, alt: ''),
        job_frame_path(@job, frame) %>
  <% end %>
</div>
```

## Frames in Job

`app/views/jobs/show.html.erb`

```erb
<h1>Job</h1>
<dl>
  <dt>Title:</dt>
  <dd><%= @job.title %></dd>
  <dt>Body:</dt>
  <dd><%= simple_format @job.body %></dd>
</dl>

<h2>Frames</h2>
<div>
  <% @job.frames.each do |frame| %>
    <%= link_to image_tag(frame.image_url, height: 100, alt: ''),
                job_frame_path(@job, frame) %>
  <% end %>
</div>
```
