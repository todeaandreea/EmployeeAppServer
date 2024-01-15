const express = require('express');
const bodyParser = require('body-parser');
const nodemailer = require("nodemailer");
const app = express();
app.use(bodyParser.json());
const cors = require('cors');

app.use(cors());


const mailjetTransport = nodemailer.createTransport({
    service: 'mailjet',
    auth: {
        user: '1a05875cf8c849ed081a6f9e4a22649c',
        pass: 'fa5bc7bb419dbb711ea3ffc17be51c7b',
    },
});

app.post('/api/email/sendToSelected', async (req, res) => {
    const { selectedEmployeesIDs, emailData } = req.body;

    // Process the email data, including sending the emails to the selected recipients using Mailjet
    const sendEmailsPromises = selectedEmployeesIDs.map((employeeNAME) => {
        return new Promise((resolve, reject) => {
            const email = {
                to: `${employeeNAME}@gmail.com`,
                from: 'brainwaveinnov@gmail.com',
                subject: emailData.subject,
                text: emailData.message,
            };

            // Use Mailjet to send the email
            mailjetTransport.send(email)
                .then(() => {
                    resolve(); // Email sent successfully
                })
                .catch((error) => {
                    reject(error); // Error sending email
                });
        });
    });

    // Wait for all emails to be sent
    try {
        await Promise.all(sendEmailsPromises);
        res.status(200).send('Emails sent successfully');
    } catch (error) {
        res.status(500).send('Error sending emails');
        console.error('Error sending emails:', error);
    }
});

// Start the server
app.listen(3000, () => console.log('Server started on port 3000'));
