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
        greendark: "#001e1d",
        green: "#4caf50",
        greenstrong: "#16a34a", // Example color
        success: "#abd1c6", // Example color
        error: "#e16162", // Example color
        darkgray: "#374151",
        gray: "d1d5db",
        graylight: "#f9fafb",
        graymedium: "#d1d5db",// Example color
        hoverBg: "#abd1c6", // Example color
        blacktext: "#000000",  // Example color
        offwhite: "#f5fafa"// Example color
      },
    },
  },
  plugins: [],
};
