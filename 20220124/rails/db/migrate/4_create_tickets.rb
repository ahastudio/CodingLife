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
