class CreateTasks < ActiveRecord::Migration[7.0]
  def change
    create_table :tasks do |t|
      t.references :project, null: false, foreign_key: true
      t.integer :sequence, null: false, default: 0
      t.string :title
      t.text :body

      t.timestamps
    end
  end
end
