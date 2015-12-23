class CreateComments < ActiveRecord::Migration[5.0]
  def change
    create_table :comments do |t|
      t.references :post, index: true, foreign_key: true
      t.text :body

      t.timestamps
    end
  end
end
