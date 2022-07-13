import { OrbitControls } from '@react-three/drei';

export default function Camera() {
  return (
    <OrbitControls
      target={[0, 1.2, 0]}
      minDistance={1}
      maxDistance={5}
      minPolarAngle={0}
      maxPolarAngle={Math.PI / 2}
    />
  );
}
