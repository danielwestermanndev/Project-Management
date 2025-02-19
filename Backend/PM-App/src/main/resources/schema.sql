-- Create the tasks table with AUTO_INCREMENT for the id
CREATE TABLE IF NOT EXISTS tasks (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(255),
    name VARCHAR(255),
    projectId BIGINT,
    status VARCHAR(50),
    userId BIGINT,
    dueDate DATE
);

-- Insert example tasks without specifying the id
INSERT INTO tasks (description, name, projectId, status, userId, dueDate) VALUES
('Design the homepage', 'Homepage Design', 1, 'In Progress', 101, '2025-03-01'),
('Develop the login feature', 'Login Development', 1, 'Not Started', 102, '2025-03-15'),
('Test the payment gateway', 'Payment Testing', 2, 'Completed', 103, '2025-02-28');