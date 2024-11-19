// src/components/TextInputComponent.jsx
import React, { useState } from 'react';

const TextInputComponent = ({ onTextSubmit, onFileSubmit }) => {
  const [text, setText] = useState('');
  const [file, setFile] = useState(null);

  const handleTextChange = (e) => setText(e.target.value);
  const handleFileChange = (e) => setFile(e.target.files[0]);

  const handleSubmit = () => {
    if (file) {
      onFileSubmit(null, file);
    } else if (text.trim() !== '') {
      onTextSubmit(text, null);
    } else {
      alert("Please enter text or upload a file to check for plagiarism.");
    }
  };

  return (
    <section className="upload-area">
      <h2>Check Your Text for Plagiarism</h2>
      <p>Upload a document or paste text below.</p>
      <input
        type="file"
        id="file-upload"
        onChange={handleFileChange}
        accept=".txt,.pdf,.doc,.docx"
      />
      <textarea
        id="text-input"
        value={text}
        onChange={handleTextChange}
        placeholder="Or paste your text here..."
      ></textarea>
      <button onClick={handleSubmit}>Check Plagiarism</button>
    </section>
  );
};

export default TextInputComponent;


