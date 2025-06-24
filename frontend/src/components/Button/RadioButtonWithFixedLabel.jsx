'use client';

import React from 'react';

export default function RadioButtonWithFixedLabel({ label, selected, onClick }) {
  const handleClick = () => {
    // Se já está selecionado, desmarca
    if (selected) {
      onClick(null);
    } else {
      onClick(label);
    }
  };

  return (
    <div
      className="flex items-center justify-between w-full gap-2 cursor-pointer hover:bg-gray-100 rounded px-2 py-1 transition"
      onClick={handleClick}
    >
      <span className="text-[14px] font-semibold text-greendark text-start min-w-0">
        {label}
      </span>
      <input
        type="radio"
        checked={selected}
        onChange={() => { }}
        className="accent-success w-4 h-4 text-success cursor-pointer flex-shrink-0"
      />
    </div>
  );
}
