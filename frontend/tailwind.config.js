/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/pages/**/*.{js,ts,jsx,tsx,mdx}",
    "./src/components/**/*.{js,ts,jsx,tsx,mdx}",
    "./src/app/**/*.{js,ts,jsx,tsx,mdx}",
  ],
  theme: {
    extend: {
      colors: {
        background: "#004643", // Example color
        main: "#e8e4e6", // Example color
        button: "#f9bc60", // Example color
        greendark: "#001e1d", // Example color
        success: "#abd1c6", // Example color
        error: "#e16162", // Example color
      },
    },
  },
  plugins: [],
};
