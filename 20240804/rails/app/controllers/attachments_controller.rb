class AttachmentsController < ApplicationController
  skip_before_action :verify_authenticity_token, only: :create

  before_action :set_attachment, only: %i[show edit update destroy]

  # GET /attachments or /attachments.json
  def index
    @attachments = Attachment.all
  end

  # GET /attachments/1 or /attachments/1.json
  def show
  end

  # GET /attachments/new
  def new
    @attachment = Attachment.new
  end

  # GET /attachments/1/edit
  def edit
  end

  # POST /attachments or /attachments.json
  def create
    uploaded_file = attachment_params[:uploaded_file]

    if uploaded_file.nil?
      raise ActiveRecord::RecordInvalid.new(Attachment.new)
    end

    @attachment = Attachment.new(
      original_filename: uploaded_file.original_filename,
      content_type: uploaded_file.content_type,
      size: uploaded_file.size,
      body: Base64.encode64(uploaded_file.read)
    )
    @attachment.save!

    respond_to do |format|
      format.html { redirect_to attachment_url(@attachment), notice: "Attachment was successfully created." }
      format.json { render :show, status: :created, location: @attachment }
    end
  rescue ActiveRecord::RecordInvalid
    respond_to do |format|
      format.html { render :new, status: :unprocessable_entity }
      format.json { render json: @attachment.errors, status: :unprocessable_entity }
    end
  end

  # PATCH/PUT /attachments/1 or /attachments/1.json
  def update
    uploaded_file = attachment_params[:uploaded_file]

    if uploaded_file.nil?
      raise ActiveRecord::RecordInvalid.new(Attachment.new)
    end

    @attachment.update!(
      original_filename: uploaded_file.original_filename,
      content_type: uploaded_file.content_type,
      size: uploaded_file.size,
      body: Base64.encode64(uploaded_file.read)
    )

    respond_to do |format|
      format.html { redirect_to attachment_url(@attachment), notice: "Attachment was successfully updated." }
      format.json { render :show, status: :ok, location: @attachment }
    end
  rescue ActiveRecord::RecordInvalid
    respond_to do |format|
      format.html { render :edit, status: :unprocessable_entity }
      format.json { render json: @attachment.errors, status: :unprocessable_entity }
    end
  end

  # DELETE /attachments/1 or /attachments/1.json
  def destroy
    @attachment.destroy!

    respond_to do |format|
      format.html { redirect_to attachments_url, notice: "Attachment was successfully destroyed." }
      format.json { head :no_content }
    end
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_attachment
      @attachment = Attachment.find(params[:id])
    end

    # Only allow a list of trusted parameters through.
    def attachment_params
      params.require(:attachment).permit(:uploaded_file)
    end
end
