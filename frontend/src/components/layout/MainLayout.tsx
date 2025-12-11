import { Outlet } from "react-router-dom";
import Header from "../Header";

export default function MainLayout(): React.ReactNode {
  return (
    <>
      <Header />
      <main style={{ padding: "20px" }}>
        <Outlet />
      </main>
    </>
  );
}
