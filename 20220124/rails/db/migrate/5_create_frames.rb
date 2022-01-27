class CreateFrames < ActiveRecord::Migration[7.0]
  def change
    create_table :frames do |t|
      t.references :task, null: false, foreign_key: true
      t.string :image_url
      t.integer :width
      t.integer :height
      t.references :job, foreign_key: true

      t.timestamps
    end
  end
end
