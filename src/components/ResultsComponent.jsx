// src/components/ResultsComponent.jsx
import React from 'react';

const ResultsComponent = ({ plagiarismPercentage }) => {
  return (
    <section className="results">
      <h3>Results:</h3>
      <div id="plagiarism-result">
        Plagiarism Percentage: {plagiarismPercentage}%
      </div>
    </section>
  );
};

export default ResultsComponent;

