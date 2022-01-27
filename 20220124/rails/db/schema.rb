# This file is auto-generated from the current state of the database. Instead
# of editing this file, please use the migrations feature of Active Record to
# incrementally modify your database, and then regenerate this schema definition.
#
# This file is the source Rails uses to define your schema when running `bin/rails
# db:schema:load`. When creating a new database, `bin/rails db:schema:load` tends to
# be faster and is potentially less error prone than running all of your
# migrations from scratch. Old migrations may fail to apply correctly if those
# migrations use external dependencies or application code.
#
# It's strongly recommended that you check this file into your version control system.

ActiveRecord::Schema.define(version: 5) do

  create_table "frames", force: :cascade do |t|
    t.integer "task_id", null: false
    t.string "image_url"
    t.integer "width"
    t.integer "height"
    t.integer "job_id"
    t.datetime "created_at", precision: 6, null: false
    t.datetime "updated_at", precision: 6, null: false
    t.index ["job_id"], name: "index_frames_on_job_id"
    t.index ["task_id"], name: "index_frames_on_task_id"
  end

  create_table "jobs", force: :cascade do |t|
    t.integer "task_id", null: false
    t.integer "sequence", default: 0, null: false
    t.string "title"
    t.text "body"
    t.datetime "created_at", precision: 6, null: false
    t.datetime "updated_at", precision: 6, null: false
    t.index ["task_id"], name: "index_jobs_on_task_id"
  end

  create_table "projects", force: :cascade do |t|
    t.string "title"
    t.text "body"
    t.datetime "created_at", precision: 6, null: false
    t.datetime "updated_at", precision: 6, null: false
  end

  create_table "tasks", force: :cascade do |t|
    t.integer "project_id", null: false
    t.integer "sequence", default: 0, null: false
    t.string "title"
    t.text "body"
    t.datetime "created_at", precision: 6, null: false
    t.datetime "updated_at", precision: 6, null: false
    t.index ["project_id"], name: "index_tasks_on_project_id"
  end

  create_table "tickets", force: :cascade do |t|
    t.integer "project_id", null: false
    t.text "body"
    t.integer "priority", default: 5, null: false
    t.string "assignee"
    t.string "target_type"
    t.integer "target_id"
    t.datetime "created_at", precision: 6, null: false
    t.datetime "updated_at", precision: 6, null: false
    t.index ["project_id"], name: "index_tickets_on_project_id"
    t.index ["target_type", "target_id"], name: "index_tickets_on_target"
  end

  add_foreign_key "frames", "jobs"
  add_foreign_key "frames", "tasks"
  add_foreign_key "jobs", "tasks"
  add_foreign_key "tasks", "projects"
  add_foreign_key "tickets", "projects"
end
