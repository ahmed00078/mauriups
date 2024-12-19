// import React from "react";
// import { Navbar } from "./Navbar";
// import { Footer } from "./Footer";

import { ReactNode } from "react";

const MainLayout = ({ children }: { children: ReactNode }) => {
  return (
    <div className="min-h-screen flex flex-col">
      {/* <Navbar /> */}
      <main className="flex-grow container mx-auto px-4 py-8">{children}</main>
      {/* <Footer /> */}
    </div>
  );
};

export { MainLayout };