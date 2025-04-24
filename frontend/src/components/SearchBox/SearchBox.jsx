'use client';

import { useState } from 'react';
import { Search } from 'lucide-react'; // ou @heroicons/react

export default function SearchBox({ value = '', onValueChange = () => {} }) {
  const [hasFocus, setHasFocus] = useState(false);

  const handleFocus = () => {
    if (!hasFocus) {
      onValueChange('');
      setHasFocus(true);
    }
  };

  const handleBlur = () => setHasFocus(false);

  return (
    <div className="w-full px-2">
      <div
        className="w-full bg-gray-200 rounded-[10px] px-4 py-2 flex items-center"
      >
        <Search className="text-greendark mr-2 w-5 h-5" />
        <input
          type="text"
          value={value}
          onChange={(e) => onValueChange(e.target.value)}
          onFocus={handleFocus}
          onBlur={handleBlur}
          placeholder={!hasFocus && value === '' ? 'Pesquisar' : ''}
          className="bg-transparent text-greendark text-[16px] outline-none flex-1 placeholder-greendark"
        />
      </div>
    </div>
  );
}


/*
"use client";

import SearchBox from "@/components/SearchBox/SearchBox";
import Image from "next/image";
import { useEffect, useState } from "react";

export default function Home() {
  const [search, setSearch] = useState('');

  return (
    <main className="flex min-h-screen flex-col items-center justify-between p-24 bg-background">
      <SearchBox value={search} onValueChange={setSearch}/>
    </main>
  );
  
}

*/