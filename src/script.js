// src/script.js
import axios from 'axios';

async function checkPlagiarism(input, isFile = false) {
  try {
    let formData = new FormData();
    if (isFile) {
      formData.append("file", input);
    } else {
      formData.append("text", input);
    }

    const response = await axios.post("http://localhost:8080/api/plagiarism/check", formData, {
      headers: {
        'Content-Type': 'multipart/form-data',
      },
    });

    // Assuming the backend returns { plagiarismPercentage: number }
    return response.data.plagiarismPercentage;
  } catch (error) {
    console.error("Error checking plagiarism:", error);
    return null;
  }
}

export default checkPlagiarism;

  