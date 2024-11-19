// src/App.js
import React, { useState } from 'react';
import Navbar from './Navbar';
import TextInputComponent from './components/TextInputComponent';
import ResultsComponent from './components/ResultsComponent';
import FooterComponent from './components/FooterComponent';
import './App.css';
import checkPlagiarism from './script';

function App() {
  const [plagiarismPercentage, setPlagiarismPercentage] = useState(null);

  const handlePlagiarismCheck = async (text, file) => {
    let result = null;

    if (file) {
      result = await checkPlagiarism(file, true);
    } else {
      result = await checkPlagiarism(text, false);
    }

    if (result !== null) {
      setPlagiarismPercentage(result);
    } else {
      alert("An error occurred while checking plagiarism. Please try again.");
    }
  };

  return (
    <div className="App">
      <Navbar />
      <main>
        <TextInputComponent onTextSubmit={handlePlagiarismCheck} onFileSubmit={handlePlagiarismCheck} />
        <ResultsComponent plagiarismPercentage={plagiarismPercentage} />
      </main>
      <FooterComponent />
    </div>
  );
}

export default App;

