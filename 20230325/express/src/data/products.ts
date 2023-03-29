import options from './options';

import { Product } from '../types';

const imageBaseUrl = 'https://ahastudio.github.io/my-image-assets/images/cbcl-products';

const products: Product[] = [
  {
    id: '0BV000PRO0001',
    categoryName: 'top',
    images: [{ url: `${imageBaseUrl}/01.jpg` }],
    name: 'CBCL 하트자수맨투맨',
    price: 128_000,
    options: [
      options['1-color'],
      options['1-size'],
    ],
    description: `
      Color: Black, Grey, Blue, Brown
      Size: ONE

      편하게 입을 수 있는 맨투맨
      고급 원단과 봉제가 들어가
      유니크함이 돋보이는 디자인
      내츄럴한 멋을 살리는 핏
    `,
  },
  {
    id: '0BV000PRO0002',
    categoryName: 'top',
    images: [{ url: `${imageBaseUrl}/02.jpg` }],
    name: 'CBCL 사틴셔츠',
    price: 118_000,
    options: [
      options['2-color'],
      options['2-size'],
    ],
    description: `
      Color: White, Black, Beige
      Size: S, M, L

      화이트 실크 소재의 살랑거리는 블라우스
      흘러 내리는듯한 실루엣으로 한층 우아한 분위기 연출
    `,
  },
  {
    id: '0BV000PRO0003',
    categoryName: 'top',
    images: [{ url: `${imageBaseUrl}/03.jpg` }],
    name: 'CBCL 레이어드 탑',
    price: 89_000,
    options: [
    ],
    description: `
      Color: Black, Beige, Cream, Charcoal
      Size: S, M

      여러 의상과 레이어드 할 때 함께 연출 가능한 이너 탑
    `,
  },
  {
    id: '0BV000PRO0004',
    categoryName: 'top',
    images: [{ url: `${imageBaseUrl}/04.jpg` }],
    name: 'CBCL 배색 후드',
    price: 89_000,
    options: [
    ],
    description: `
      Color: Beige, Khaki, Charcoal
      Size: One Size

      배색 조합의 강렬함과 트렌디함이 강조된 크롭 후드
    `,
  },
  {
    id: '0BV000PRO0005',
    categoryName: 'outer',
    images: [{ url: `${imageBaseUrl}/05.jpg` }],
    name: '박시롱코트',
    price: 298_000,
    options: [
    ],
    description: `
      Color: Navy, Beige, Yellow
      Size: M, L

      시크함이 어우러지는 소재의 오버핏 코트
      다양한 이너와도 잘 어울리는 트렌디 디자인
    `,
  },
  {
    id: '0BV000PRO0006',
    categoryName: 'outer',
    images: [{ url: `${imageBaseUrl}/06.jpg` }],
    name: 'CBCL 레귤러핏 야구점퍼',
    price: 397_000,
    options: [
    ],
    description: `
      Color: Black, Brown, Gray
      Size: M, L

      스티치 포인트가 살아있는 유니크한 야구점퍼
      소매 소재의 고급스러움이 풍기는 아름다움
    `,
  },
  {
    id: '0BV000PRO0007',
    categoryName: 'outer',
    images: [{ url: `${imageBaseUrl}/07.jpg` }],
    name: 'CBCL 핀턱자수후드',
    price: 158_000,
    options: [
    ],
    description: `
      Color: Brown, Khaki, Charcoal
      Size: S, M, L

      라이트한 컬러매치와 핀턱 디자인으로 밝은 분위기 연출
    `,
  },
  {
    id: '0BV000PRO0008',
    categoryName: 'bottom',
    images: [{ url: `${imageBaseUrl}/08.jpg` }],
    name: '밴딩스커트',
    price: 966_000,
    options: [
    ],
    description: `
      Color: Pink
      Size: S, M, L

      스트라이프가 돋보이는 스커트
      봄 소재의 라이트한 두께
    `,
  },
  {
    id: '0BV000PRO0009',
    categoryName: 'bottom',
    images: [{ url: `${imageBaseUrl}/09.jpg` }],
    name: 'CBCL 하트자수셋업조거',
    price: 138_000,
    options: [
    ],
    description: `
      Color: Navy, Brown, Gray, Black
      Size: S, M, L

      도톰하고 편한 소재의 스웻 팬츠
      유니크한 밑단으로 핏이 예쁜 팬츠
    `,
  },
  {
    id: '0BV000PRO0010',
    categoryName: 'acc',
    images: [{ url: `${imageBaseUrl}/10.jpg` }],
    name: 'CBCL EARRING Silver',
    price: 62_000,
    options: [
    ],
    description: `
      고급스러운 광채가 나는 이어링
    `,
  },
  {
    id: '0BV000PRO0011',
    categoryName: 'acc',
    images: [{ url: `${imageBaseUrl}/11.jpg` }],
    name: 'CBCL EARRING Green',
    price: 82_000,
    options: [
    ],
    description: `
      그린 컬러의 로고 이미지가 돋보이는 이어링
    `,
  },
  {
    id: '0BV000PRO0012',
    categoryName: 'acc',
    images: [{ url: `${imageBaseUrl}/12.jpg` }],
    name: 'CBCL EARRING Purple',
    price: 72_000,
    options: [],
    description: `
      퍼플과 블랙의 조화가 돋보이는 이어링
    `,
  },
];

export default products;
