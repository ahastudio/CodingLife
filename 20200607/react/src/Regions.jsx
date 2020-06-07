import React from 'react';

export default function Regions({
  regions, region: selectedRegion, onClick,
}) {
  return (
    <ul>
      {regions.map((region) => (
        <li key={region.id}>
          <button
            type="button"
            onClick={() => onClick(region)}
          >
            {region.name}
          </button>
          {selectedRegion && region.id === selectedRegion.id ? '(V)' : ''}
        </li>
      ))}
    </ul>
  );
}
