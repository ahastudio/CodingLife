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

const vertexShader = `
  varying vec2 vCoord;

  void main() {
    vCoord = uv;
    gl_Position = projectionMatrix * modelViewMatrix * vec4(position, 1.0);
  }
`;

const fragmentShader = `
  uniform sampler2D uImage;
  uniform sampler2D uFilter;
  varying vec2 vCoord;

  vec4 filterColor(vec4 color) {
    float r = floor(color.r * 255.0);
    float g = floor(color.g * 255.0);
    float b = floor(color.b * 255.0);

    float x = r + mod(b, 16.0) * 256.0 + 0.5;
    float y = g + floor(b / 16.0) * 256.0 + 0.5;

    vec2 coord = vec2(x / 4096.0, 1.0 - (y / 4096.0));

    return texture2D(uFilter, coord);
  }

  void main() {
    vec4 color = texture2D(uImage, vCoord);
    if (vCoord.x > 0.0) {
      color = filterColor(color);
    }
    gl_FragColor = color;
  }
`;

function createTexture(imageData) {
  const texture = new THREE.Texture(imageData);
  texture.minFilter = THREE.NearestFilter;
  texture.magFilter = THREE.NearestFilter;
  texture.generateMipmaps = false;
  texture.needsUpdate = true;
  return texture;
}

function drawImageData({ imageData, filterData, canvas }) {
  const { width, height } = imageData;
  const halfWidth = width / 2;
  const halfHeight = height / 2;

  const renderer = new THREE.WebGLRenderer({
    canvas,
    antialias: false,
    alpha: false,
  });

  const scene = new THREE.Scene();
  const camera = new THREE.OrthographicCamera(
    -halfWidth, halfWidth, halfHeight, -halfHeight, 1, 1000
  );
  camera.position.z = 10;
  scene.add(camera);

  const geometry = new THREE.PlaneGeometry(width, height);
  const material = new THREE.ShaderMaterial({
    uniforms: {
      uImage: { value: createTexture(imageData) },
      uFilter: { value: createTexture(filterData) },
    },
    vertexShader,
    fragmentShader,
  });

  const mesh = new THREE.Mesh(geometry, material);
  scene.add(mesh);

  renderer.render(scene, camera);
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

    drawImageData({
      imageData,
      filterData: this.filterData,
      canvas: targetCanvas,
    });
  }
}
