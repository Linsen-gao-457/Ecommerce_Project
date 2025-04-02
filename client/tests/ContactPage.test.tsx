import { render, screen } from "@testing-library/react";
import { describe, it, expect } from "vitest";
import ContactPage from "../src/features/contact/ContactPage";

describe("ContactPage Component", () => {
  it("renders the Contact Section heading", () => {
    render(<ContactPage />);
    expect(screen.getByText(/Contact Section/i)).toBeInTheDocument();
  });

  it("renders the heading with the correct role", () => {
    render(<ContactPage />);
    const headings = screen.getAllByRole("heading", { level: 2 }); // Get all <h2> elements
    expect(headings).toHaveLength(1); // Ensure there is only one <h2>
    expect(headings[0]).toHaveTextContent("Contact Section"); // Check the content of the <h2>
  });
});
