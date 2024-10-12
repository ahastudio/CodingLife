function readFileAsDataURL(file) {
  return new Promise((resolve, reject) => {
    const reader = new FileReader();

    reader.onload = () => {
      resolve(reader.result);
    };

    reader.onerror = reject;

    reader.readAsDataURL(file);
  });
}

function createImageFromAsDataURL(dataURL) {
  return new Promise((resolve, reject) => {
    const image = new Image();

    image.onload = () => {
      resolve(image);
    };

    image.onerror = reject;

    image.src = dataURL;
  });
}

function drawImage({ image, canvas }) {
  const context = canvas.getContext('2d');
  context.drawImage(image, 0, 0);
}

function getImageData(canvas) {
  const context = canvas.getContext('2d');
  return context.getImageData(0, 0, canvas.width, canvas.height);
}

function drawImageData({ imageData, canvas }) {
  const context = canvas.getContext('2d');
  context.putImageData(imageData, 0, 0);
}

function calculateIndex(r, g, b) {
  const x = r + (b % 16) * 256;
  const y = g + Math.floor(b / 16) * 256;
  return y * 256 * 16 + x;
}

class Application {
  filterData = null;

  async selectFilter(event) {
    const file = event.target.files[0];
    if (!file) {
      return;
    }

    const dataURL = await readFileAsDataURL(file);
    const image = await createImageFromAsDataURL(dataURL);

    const filterCanvas = this.$refs.filterCanvas;
    filterCanvas.width = image.width;
    filterCanvas.height = image.height;

    drawImage({ image, canvas: filterCanvas });

    this.filterData = getImageData(filterCanvas);
  }

  async selectImage(event) {
    if (!this.filterData) {
      alert('Please select a filter first.');
      return;
    }

    const file = event.target.files[0];
    if (!file) {
      return;
    }

    const dataURL = await readFileAsDataURL(file);
    const image = await createImageFromAsDataURL(dataURL);

    const sourceCanvas = this.$refs.sourceCanvas;
    sourceCanvas.width = image.width;
    sourceCanvas.height = image.height;

    const targetCanvas = this.$refs.targetCanvas;
    targetCanvas.width = image.width;
    targetCanvas.height = image.height;

    drawImage({ image, canvas: sourceCanvas });

    const imageData = getImageData(sourceCanvas);

    console.log('Start processing image...');
    const data = imageData.data;
    for (let i = 0; i < data.length; i += 4) {
      const [r, g, b] = data.slice(i, i + 3);
      const index = calculateIndex(r, g, b);
      for (let j = 0; j < 3; j++) {
        data[i + j] = this.filterData.data[index * 4 + j];
      }
    }
    console.log('End processing image!');

    drawImageData({ imageData, canvas: targetCanvas });
  }
}
