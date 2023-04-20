import { useRef } from 'react';

import { useEffectOnce } from 'usehooks-ts';

import styled from 'styled-components';

const Container = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, .5);

  div {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    width: 80%;
    height: 80%;
    max-width: 50rem;
    background: ${(props) => props.theme.colors.background};
  }
`;

type AddressSearchProps = {
  close: ()=> void;
  changeAddress: ({ address, postalCode }: {
    address: string;
    postalCode: string;
  }) => void;
}

export default function AddressSearch({
  close, changeAddress,
}: AddressSearchProps) {
  const refElement = useRef<HTMLDivElement>(null);

  useEffectOnce(() => {
    new daum.Postcode({
      oncomplete(data) {
        const { address, zonecode: postalCode } = data;
        changeAddress({ address, postalCode });
        close();
      },
      width: '100%',
      height: '100%',
    }).embed(refElement.current);
  });

  return (
    <Container id="address-search-container" onClick={close}>
      <div ref={refElement} />
    </Container>
  );
}
