import * as THREE from 'three';

import { Canvas } from '@react-three/fiber';
import { Sky } from '@react-three/drei';
import {
  EffectComposer, DepthOfField, Bloom,
} from '@react-three/postprocessing';

import Room from './Room';
import Avatar from './Avatar';
import Camera from './Camera';

export default function Scene() {
  const sunPosition = [-5, 5, -1];
  return (
    <Canvas
      shadows={{
        enabled: true,
        type: THREE.PCFSoftShadowMap,
      }}
      camera={{
        position: [0.6, 1, 0.9],
        near: 0.1,
        far: 10000,
        fov: 60,
      }}
    >
      <color attach="background" args={['#888']} />
      <ambientLight intensity={0.05} />
      <directionalLight
        color="#88CCFF"
        position={sunPosition}
        intensity={1.5}
        castShadow
        shadow-mapSize={[1024, 1024]}
        shadow-bias={-0.0001}
      />
      <hemisphereLight
        color="#FFBBE1"
        groundColor="#080820"
        intensity={0.3}
      />
      <Sky
        scale={100}
        sunPosition={sunPosition}
      />
      <Room />
      <Avatar />
      <EffectComposer>
        <DepthOfField
          focusDistance={0}
          focalLength={0.002}
          bokehScale={2}
          height={512}
        />
        <Bloom
          luminanceThreshold={0.5}
          luminanceSmoothing={0.1}
          height={200}
        />
      </EffectComposer>
      <Camera />
    </Canvas>
  );
}
