import React, { useEffect, useState } from "react";

const Consume = () => {
  const [data, setData] = useState(null);

  useEffect(() => {
    fetch("https://rxnav.nlm.nih.gov/REST/interaction/interaction.json?rxcui=341248")
      .then((res) => res.json())
      .then((res) => setData(res))
      .catch(() => setData(null));
  }, []);

  if (!data) {
    return (
    <div style={{ textAlign: "center", padding: "20px", fontWeight: "bold" }}>
        No data found
      </div>

    );
  } 

  return (
    <div>
      <h2>API Response</h2>
      <pre>{JSON.stringify(data, null, 2)}</pre>
    </div>
  );
};

export default Consume;
