class CreateAttachments < ActiveRecord::Migration[7.1]
  def change
    create_table :attachments do |t|
      t.string :original_filename
      t.string :content_type
      t.integer :size
      t.text :body

      t.timestamps
    end
  end
end
