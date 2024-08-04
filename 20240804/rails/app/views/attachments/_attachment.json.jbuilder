json.extract! attachment, :id, :original_filename, :content_type, :size, :created_at, :updated_at
json.url attachment_url(attachment, format: :json)
