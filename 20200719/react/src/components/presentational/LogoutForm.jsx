import React from 'react';

function LogoutForm({ onClick }) {
  return (
    <>
      <button
        type="button"
        onClick={onClick}
      >
        Log out
      </button>
    </>
  );
}

export default React.memo(LogoutForm);
