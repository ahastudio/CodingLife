import React from 'react';

import { List, Item } from '../styles/list-components';

function Regions({ regions, selectedRegion, onClick }) {
  return (
    <List>
      {regions.map((region) => (
        <Item
          key={region.id}
          active={selectedRegion && region.id === selectedRegion.id}
        >
          <button
            type="button"
            onClick={() => onClick(region.id)}
          >
            {region.name}
            {selectedRegion ? (
              <>
                {region.id === selectedRegion.id ? '(V)' : null}
              </>
            ) : null}
          </button>
        </Item>
      ))}
    </List>
  );
}

export default React.memo(Regions);
