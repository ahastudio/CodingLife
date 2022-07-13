import { useLayoutEffect } from 'react';

import { useGLTF, useAnimations } from '@react-three/drei';

const baseUrl = process.env.ASSET_BASE_URL;

const modelUrl = `${baseUrl}/azri_run_animation/scene.gltf`;

useGLTF.preload(modelUrl);

export default function Avatar() {
  const { scene, nodes, animations } = useGLTF(modelUrl);

  const { actions } = useAnimations(animations, scene);

  useLayoutEffect(() => {
    Object.values(nodes).forEach((node) => {
      node.castShadow = true;
      node.receiveShadow = true;
    });

    const action = actions.Run_Animation2;
    action.play();
  }, []);

  return (
    <primitive object={scene} scale={0.01} />
  );
}
