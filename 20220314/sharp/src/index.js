const { promisify } = require('util');
const path = require('path');
const fs = require('fs');
const glob = require('glob');
const sharp = require('sharp');

const { log } = console;

async function main() {
  const inputPath = path.join(__dirname, '../images/original');
  const outputPath = path.join(__dirname, '../images/thumbnail');
  const filenames = await promisify(glob)(`${inputPath}/**/*.?(jpg|png)`);

  await Promise.all((
    filenames.map(async (filename) => {
      const dirname = path.dirname(filename).replace(inputPath, '');
      const basename = path.basename(filename);

      log(`File: ${dirname}/${basename}`);

      fs.promises.mkdir(path.join(outputPath, dirname), {
        recursive: true,
      });

      await sharp(filename)
        .resize({ width: 200, height: 200 })
        .toFile(path.join(outputPath, dirname, basename));
    })
  ));
}

main();
