class Service
  NAMES = %w[add-block get-block-count get-block-id get-block].freeze

  def initialize
    @blocks = {}
    @block_ids = []
  end

  def call(name, params)
    return { error: 'method-not-found' } unless NAMES.include?(name)

    args = params.each_with_object({}) { |i, o| o[i[0].to_sym] = i[1] }
    send('_' + name.tr('-', '_'), args)
  end

  private

  def _add_block(id:)
    return { error: 'duplicated block id' } if @blocks[id]

    @blocks[id] = {
      id: id,
      previous_block_id: @block_ids.last,
      height: @block_ids.size + 1,
      timestamp: Time.now.to_i
    }
    @block_ids << id

    { result: @block_ids.size }
  end

  def _get_block_count(_params)
    { result: @block_ids.size }
  end

  def _get_block_id(height:)
    { result: @block_ids[height.to_i] }
  end

  def _get_block(id:)
    { result: @blocks[id] }
  end
end
