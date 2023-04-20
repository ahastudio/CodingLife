import { useBoolean } from 'usehooks-ts';

import styled from 'styled-components';

import AddressSearch from './AddressSearch';

import TextBox from '../ui/TextBox';
import Button from '../ui/Button';

import useOrderFormStore from '../../hooks/useOrderFormStore';

const Container = styled.div`
  h3 {
    font-size: 2rem;
  }

  input {
    width: 50rem;
  }
`;

const PostalCodeField = styled.div`
  > div {
    display: inline-block;
    margin-right: 1rem;
  }

  input {
    width: 10rem;
  }
`;

export default function ShippingForm() {
  const {
    value: searching, setTrue: openSearch, setFalse: closeSearch,
  } = useBoolean();

  const [{
    name, address1, address2, postalCode, phoneNumber,
  }, store] = useOrderFormStore();

  const handleClickSearchPostalCode = () => {
    openSearch();
  };

  const handleChangeName = (value: string) => {
    store.changeName(value);
  };

  const handleChangeAddress1 = (value: {
    address: string;
    postalCode: string;
  }) => {
    store.changeAddress1(value.address, value.postalCode);
  };

  const handleChangeAddress2 = (value: string) => {
    store.changeAddress2(value);
  };

  const handlePhonNumber = (value: string) => {
    store.changePhoneNumber(value);
  };

  return (
    <Container>
      <h3>받는 사람</h3>
      <TextBox
        label="이름"
        placeholder="받는 분 이름"
        value={name}
        onChange={handleChangeName}
      />
      <PostalCodeField>
        <TextBox
          label="우편번호"
          value={postalCode}
          readOnly
        />
        <Button onClick={handleClickSearchPostalCode}>
          우편번호 검색
        </Button>
      </PostalCodeField>
      <TextBox
        label="주소"
        value={address1}
        readOnly
      />
      <TextBox
        label="상세 주소"
        value={address2}
        onChange={handleChangeAddress2}
      />
      <TextBox
        label="전화번호"
        type="tel"
        value={phoneNumber}
        onChange={handlePhonNumber}
      />
      {searching && (
        <AddressSearch
          close={closeSearch}
          changeAddress={handleChangeAddress1}
        />
      )}
    </Container>
  );
}
