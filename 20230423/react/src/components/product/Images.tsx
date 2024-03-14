import TextBox from '../ui/TextBox';
import Button from '../ui/Button';

import ProductFormStore from '../../stores/ProductFormStore';

import { Image } from '../../types';

import { key } from '../../utils';

type ImagesProps = {
  images: Image[];
  store: Readonly<ProductFormStore>;
}

export default function Images({ images, store }: ImagesProps) {
  return (
    <ul>
      {images.map((image, index) => (
        <li key={key('', index)}>
          <TextBox
            label={`이미지 #${index + 1}`}
            value={image.url}
            onChange={(value) => store.changeImageUrl(index, value)}
          />
          <Button onClick={() => store.removeImage(index)} leftpad>
            이미지 삭제
          </Button>
        </li>
      ))}
      <li>
        <Button onClick={() => store.addImage()} leftpad>
          이미지 추가
        </Button>
      </li>
    </ul>
  );
}
