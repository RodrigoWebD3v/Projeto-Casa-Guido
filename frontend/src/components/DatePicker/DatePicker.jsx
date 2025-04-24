'use client';

import React, { useState, useEffect } from 'react';
import DatePicker from 'react-datepicker';
import { format } from 'date-fns';

export default function DatePickerInput({
  title,
  value = '',
  onChange = () => {},
}) {
  const [selectedDate, setSelectedDate] = useState(new Date());

  useEffect(() => {
    onChange(format(selectedDate, 'dd/MM/yyyy'));
  }, [selectedDate]);

  return (
    <div className="w-full px-5">
      <label className="text-[14px] text-greendark font-medium block mb-1">
        {title}
      </label>
      <div
        className="w-full h-[45px] bg-success border border-success rounded-md flex items-center justify-center cursor-pointer"
      >
        <DatePicker
          selected={selectedDate}
          onChange={(date) => setSelectedDate(date)}
          dateFormat="dd/MM/yyyy"
          className="w-full bg-transparent text-background px-4 text-[16px] font-semibold focus:outline-none"
          calendarClassName="rounded-lg border-none shadow-lg"
        />
      </div>
    </div>
  );
}

/*
"use client";

import DatePickerInput from "@/components/DatePicker/DatePicker";
import { useState } from "react";

export default function Home() {
  const [data, setData] = useState('');

  return (
    <main className="flex min-h-screen flex-col items-center justify-between p-24 bg-background">
     <DatePickerInput
        title="Data de nascimento"
        value={data}
        onChange={(novaData) => setData(novaData)}
      />
    </main>
  );
}
*/