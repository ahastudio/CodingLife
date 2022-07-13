import { useLayoutEffect } from 'react';

import { useGLTF } from '@react-three/drei';

const baseUrl = process.env.ASSET_BASE_URL;

const modelUrl = `${baseUrl}/throne_room/scene.gltf`;

useGLTF.preload(modelUrl);

export default function Room() {
  const { scene, nodes } = useGLTF(modelUrl);

  useLayoutEffect(() => {
    Object.values(nodes).forEach((node) => {
      node.castShadow = true;
      node.receiveShadow = true;
    });
  }, []);

  return (
    <primitive object={scene} scale={0.03} />
  );
}
