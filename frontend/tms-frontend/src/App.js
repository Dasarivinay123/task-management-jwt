import { Routes, Route } from 'react-router-dom';
import './App.css'
import HeaderComponet from './components/HeaderComponent'
import FooterComponent from './components/FooterComponent'
import LogInComponent from './components/LogInComponent';
import RegisterComponent from './components/RegisterComponent'
import TaskList from './components/TaskList';
import AddTaskComponent from './components/AddTaskComponent';
function App() {
  return (
    <>
      <HeaderComponet />
      <Routes>
        <Route path="/login" element={<LogInComponent />} />
        <Route path="/register" element={<RegisterComponent />} />

        <Route path="/" element={<LogInComponent />} />
        <Route path="/tasks" element={<TaskList />} />
        <Route path="/add-task" element={<AddTaskComponent />}
        />
        <Route path="/edit-task/:id" element={<AddTaskComponent />} />

      </Routes>
      <FooterComponent />
    </>
  );
}

export default App;