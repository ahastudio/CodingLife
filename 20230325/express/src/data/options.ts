import { ProductOption } from '../types';

const options: Record<string, ProductOption> = {
  '1-color': {
    id: '0BV000OPT0001',
    name: '컬러',
    items: [
      { id: '0BV000ITEM001', name: 'black' },
      { id: '0BV000ITEM002', name: 'grey' },
      { id: '0BV000ITEM003', name: 'blue' },
      { id: '0BV000ITEM004', name: 'brown' },
    ],
  },
  '1-size': {
    id: '0BV000OPT0002',
    name: '사이즈',
    items: [
      { id: '0BV000ITEM005', name: 'ONE' },
    ],
  },
  '2-color': {
    id: '0BV000OPT0003',
    name: '컬러',
    items: [
      { id: '0BV000ITEM006', name: 'white' },
      { id: '0BV000ITEM007', name: 'black' },
      { id: '0BV000ITEM008', name: 'beige' },
    ],
  },
  '2-size': {
    id: '0BV000OPT0004',
    name: '사이즈',
    items: [
      { id: '0BV000ITEM009', name: 'S' },
      { id: '0BV000ITEM010', name: 'M' },
      { id: '0BV000ITEM011', name: 'L' },
    ],
  },
};

export default options;
