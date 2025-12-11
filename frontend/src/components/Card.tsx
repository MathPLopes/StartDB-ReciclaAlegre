interface CardProps {
  title: string;
  text: string;
}

export function Card({ title, text }: CardProps) {
  return (
    <div className="card">
      <h3>{title}</h3>
      <p>{text}</p>
    </div>
  );
}
