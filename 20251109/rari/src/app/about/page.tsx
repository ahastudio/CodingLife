import type { PageProps } from 'rari/client';

export const metadata = {
  title: 'About | Rari Demo',
  description: 'Learn more about this Rari Demo Application',
};

export default function AboutPage(_params: PageProps) {
  return (
    <div className="space-y-6">
      <div className="bg-white rounded-lg shadow-sm border border-gray-200 p-8">
        <h1 className="text-4xl font-bold text-gray-900 mb-4">About</h1>
        <p className="text-lg text-gray-600 mb-4">
          어바웃 페이지에 오신 것을 환영합니다!
        </p>
      </div>
    </div>
  );
}
