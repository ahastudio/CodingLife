require 'sequel'
require 'active_support/all'

DB = Sequel.sqlite

DB.create_table :my_items do
  primary_key :id
  String :name
  Float :price
end

#

class Model
  def initialize
    collection.columns.each do |column|
      define_attribute(column)
    end
  end

  def self.collection
    DB[name.underscore.pluralize.to_sym]
  end

  def collection
    self.class.collection
  end

  def attributes
    @attributes ||= {}
  end

  def save
    if id.nil?
      self.id = collection.insert(attributes)
    else
      collection.where(id: id).update(attributes)
    end
  end

  def self.find(id)
    attributes = collection.where(id: id).first
    return if attributes.nil?
    attributes.each_with_object(new) do |(k, v), model|
      model.send("#{k}=", v)
    end
  end

  private

  def define_attribute(name)
    return if name.to_s.include?('\'')
    class_eval %(
      def #{name}=(value)
        attributes['#{name}'] = value
      end

      def #{name}
        attributes['#{name}']
      end
    )
  end
end

#

class MyItem < Model
end

puts '-------------------------------------'
item = MyItem.new
item.name = 'Product'
item.price = 10_000
item.save
puts item.id, item.name, item.price

puts '-------------------------------------'
item = MyItem.find(item.id)
item.price = 20_000
item.save
puts item.id, item.name, item.price

puts '-------------------------------------'
item = MyItem.find(-1)
puts item.nil?
