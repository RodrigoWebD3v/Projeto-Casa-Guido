'use client';

import React, { useState, useEffect } from 'react';
import DatePicker from 'react-datepicker';
import { format } from 'date-fns';
import { Calendar, X } from 'lucide-react';
import 'react-datepicker/dist/react-datepicker.css';

export default function DatePickerInput({
  title,
  value = null, // agora espera Date ou null
  onChange = () => { },
  minDate = null,
}) {
  // Controla o estado local para controlar o valor do datepicker
  const [selectedDate, setSelectedDate] = useState(value);

  useEffect(() => {
    setSelectedDate(value);
  }, [value]);

  const CustomInput = React.forwardRef(({ value, onClick }, ref) => (
    <div
      onClick={onClick}
      ref={ref}
      className="w-full h-[45px] bg-success border border-success rounded-md flex items-center justify-between cursor-pointer px-4 relative"
    >
      <span className={`text-[16px] font-semibold ${value ? 'text-background' : 'text-background'}`}>
        {value || 'Selecione a data'}
      </span>
      <div className="flex items-center gap-2">
        {value && (
          <button
            type="button"
            onClick={(e) => {
              e.stopPropagation();
              setSelectedDate(null);
              onChange(null);
            }}
            className="text-red-500 hover:text-red-700 transition"
            title="Limpar data"
          >
            <X size={16} />
          </button>
        )}
        <Calendar size={20} className="text-background" />
      </div>
    </div>
  ));
  CustomInput.displayName = 'CustomInput';

  return (
    <div className="w-full px-5">
      {title && (
        <label className="text-[14px] text-background font-medium block mb-1">
          {title}
        </label>
      )}

      <DatePicker
        selected={selectedDate}
        onChange={(date) => {
          setSelectedDate(date);
          onChange(date);
        }}
        dateFormat="dd/MM/yyyy"
        customInput={<CustomInput />}
        calendarClassName="rounded-lg border-none shadow-lg"
        minDate={minDate}
        placeholderText="Selecione a data"
      />
    </div>
  );
}
