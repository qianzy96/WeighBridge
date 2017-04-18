function getHomePage()
{
    var formattedOutput = Home.getHomePage();
    document.getElementById('mainUpdatePanel').innerHTML = formattedOutput['html'];
}
function loadSwingEdition()
{
    Home.launchSwingVersion();
}
function loadHTML5Edition()
{
    var formattedOutput = Home.launchHTML5Version();
    document.getElementById('mainUpdatePanel').innerHTML = formattedOutput['html'];
}
function exit()
{
    Home.exit();
}
function selectWeighBridgeDocketType()
{
    var formattedOutput = WeighBridge.setWeightBridgeDocketType();
    document.getElementById('mainUpdatePanel').innerHTML = formattedOutput['html'];
}
function selectWeighBridgeConsignee(docketTypeIdentifier)
{
    var formattedOutput = WeighBridge.setConsignee(docketTypeIdentifier);
    document.getElementById('mainUpdatePanel').innerHTML = formattedOutput['html'];
}
function addNewConsignee()
{
    var formattedOutput = WeighBridge.addNewConsignee();
    document.getElementById('mainUpdatePanel').innerHTML = formattedOutput['html'];
}
function saveNewConsignee()
{
    var firstName = document.getElementById('firstName').value;
    var lastName = document.getElementById('lastName').value;
    var formattedOutput = WeighBridge.saveNewConsignee(firstName, lastName);
    if(formattedOutput['html'])
        document.getElementById('mainUpdatePanel').innerHTML = formattedOutput['html'];
    if(formattedOutput['response'] === "success")
        displaySuccessNotification(formattedOutput['title'], formattedOutput['content']);
    if(formattedOutput['response'] === "error")
        displayErrorNotification(formattedOutput['title'], formattedOutput['content']);
}
function selectWeighBridgeDriver(consigneeIdentifier)
{
    var formattedOutput = WeighBridge.setDriver(consigneeIdentifier);
    document.getElementById('mainUpdatePanel').innerHTML = formattedOutput['html'];
}
function addNewDriver()
{
    var formattedOutput = WeighBridge.addNewDriver();
    document.getElementById('mainUpdatePanel').innerHTML = formattedOutput['html'];
}
function saveNewDriver()
{
    var firstName = document.getElementById('firstName').value;
    var lastName = document.getElementById('lastName').value;
    var formattedOutput = WeighBridge.saveNewDriver(firstName, lastName);
    if(formattedOutput['html'])
        document.getElementById('mainUpdatePanel').innerHTML = formattedOutput['html'];
    if(formattedOutput['response'] === 'success')
        displaySuccessNotification(formattedOutput['title'], formattedOutput['content']);
    if(formattedOutput['response'] === 'error')
        displayErrorNotification(formattedOutput['title'], formattedOutput['content']);
}
function selectWeighBridgeCommodity(driverIdentifier)
{
    var formattedOutput = WeighBridge.setCommodity(driverIdentifier);
    document.getElementById('mainUpdatePanel').innerHTML = formattedOutput['html'];
}
function addNewCommodity()
{
    var formattedOutput = WeighBridge.addNewCommodity();
    document.getElementById('mainUpdatePanel').innerHTML = formattedOutput['html'];
}
function saveNewCommodity()
{
    var commodity = document.getElementById('commodity').value;
    var formattedOutput = WeighBridge.saveNewCommodity(commodity);
    if(formattedOutput['html'])
        document.getElementById('mainUpdatePanel').innerHTML = formattedOutput['html'];
    if(formattedOutput['response'] === 'success')
        displaySuccessNotification(formattedOutput['title'], formattedOutput['content']);
    if(formattedOutput['response'] === 'error')
        displayErrorNotification(formattedOutput['title'], formattedOutput['content']);
}
function obtainWeighBridgeFirstWeight(commodityIdentifier)
{
    var formattedOutput = WeighBridge.completeFirstWeight(commodityIdentifier);
    if(formattedOutput['html'])
        document.getElementById('mainUpdatePanel').innerHTML = formattedOutput['html'];
    if(formattedOutput['response'] === "success")
        displaySuccessNotification(formattedOutput['title'], formattedOutput['content']);
}
function getDocketsAwaitingSecondWeighing()
{
    var formattedOutput = WeighBridge.getDocketsAwaitingSecondWeighing();
    document.getElementById('mainUpdatePanel').innerHTML = formattedOutput['html'];
}
function getLogOnPage()
{
    var formattedOutput = LogOn.createLogOnPage();
    document.getElementById('mainUpdatePanel').innerHTML = formattedOutput['html'];
}
function processUserLogOn()
{
    var usernameTextFieldValue = document.getElementById("username").value;
    var passwordTextFieldValue = document.getElementById("password").value;
    var formattedOutput = LogOn.processLogOn(usernameTextFieldValue, passwordTextFieldValue);
    if(formattedOutput['html'])
        document.getElementById('mainUpdatePanel').innerHTML = formattedOutput['html'];
    if(formattedOutput['response'] === "success")
        displaySuccessNotification(formattedOutput['title'], formattedOutput['content']);
    if(formattedOutput['response'] === "error")
        displayErrorNotification(formattedOutput['title'], formattedOutput['content']);
}
function getRegistrationPage()
{
    var formattedOutput = Registration.createRegistrationPage();
    document.getElementById('mainUpdatePanel').innerHTML = formattedOutput['html'];
}
function processUserRegistration()
{
    var usernameTextFieldValue = document.getElementById("username").value;
    var passwordTextFieldValue = document.getElementById("password").value;
    var firstNameTextFieldValue = document.getElementById("firstName").value;
    var lastNameTextFieldValue = document.getElementById("lastName").value;
    var emailAddressTextFieldValue = document.getElementById("emailAddress").value;
    var phoneNumberTextFieldValue = document.getElementById("phoneNumber").value;
    var formattedOutput = Registration.processRegistration(usernameTextFieldValue, passwordTextFieldValue, firstNameTextFieldValue, lastNameTextFieldValue,
    emailAddressTextFieldValue, phoneNumberTextFieldValue);
    if(formattedOutput['html'])
        document.getElementById('mainUpdatePanel').innerHTML = formattedOutput['html'];
    if(formattedOutput['response'] === "success")
        displaySuccessNotification(formattedOutput['title'], formattedOutput['content']);
    if(formattedOutput['response'] === "error")
        displayErrorNotification(formattedOutput['title'], formattedOutput['content']);
}
function getPortalPage()
{
    var formattedOutput = Portal.loadPortalPage();
    document.getElementById('mainUpdatePanel').innerHTML = formattedOutput['html'];
}
function getEditUserSettingsPage()
{
    var formattedOutput = Portal.editUserDetails();
    document.getElementById('mainUpdatePanel').innerHTML = formattedOutput['html'];
}
function editDetail(identifier, title, icon)
{
    var formattedOutput = Portal.editDetail(identifier, title, icon);
    document.getElementById('editDetailPanel').innerHTML = formattedOutput['html'];
}
function saveUpdatedDetail(identifier)
{
    var updatedDetail = document.getElementById('editDetail').value;
    var formattedOutput = Portal.saveUpdatedDetail(identifier, updatedDetail);
    if(formattedOutput['html'])
        document.getElementById('mainUpdatePanel').innerHTML = formattedOutput['html'];
    if(formattedOutput['response'] === "success")
        displaySuccessNotification(formattedOutput['title'], formattedOutput['content']);
    if(formattedOutput['response'] === "error")
        displayErrorNotification(formattedOutput['title'], formattedOutput['content']);
}
function getCalculatorPage()
{
    var formattedOutput = Calculator.createCalculatorPage();
    document.getElementById('mainUpdatePanel').innerHTML = formattedOutput['html'];
}
function createRationStepOne()
{
    var formattedOutput = Calculator.createRationStepOne();
    document.getElementById('calculatorUpdatePanel').innerHTML = formattedOutput['html'];
}
function createRationStepTwo()
{
    var rationTitle = document.getElementById('rationTitleTextField').value;
    var rationType = getSelectedItemFromDropDown(0);
    //document.getElementsByClassName("select2-selection__rendered")[0].innerText.substring(1);
    var formattedOutput = Calculator.createRationStepTwo(rationTitle, rationType);
    if(formattedOutput['html'])
        document.getElementById('calculatorUpdatePanel').innerHTML = formattedOutput['html'];
    if(formattedOutput['response'] === 'error')
        displayErrorNotification(formattedOutput['title'], formattedOutput['content']);
}
function addCommodityToRation()
{
    var commodityTitle = document.getElementsByClassName("select2-selection_rendered")[0].innerText.substring(1);
    var formattedOutput = Calculator.addCommodityToRation(commodityTitle);
    if(formattedOutput['html'])
        document.getElementById('addRationComponentUpdatePanel').innerHTML = formattedOutput['html'];
}
function addCommodityTitleAndQuantityToRation()
{
    var commodityTitle = document.getElementById('commoditiesDropDown').children[1].children[0].children[0].children[0].innerText.substring(1);
    var commodityQuantity = document.getElementById("commodityQuantityTextBox").value;
    var formattedOutput = Calculator.addCommodityWithQuantityToRation(commodityTitle, commodityQuantity);
    if(formattedOutput['html'])
        document.getElementById('addRationComponentUpdatePanel').innerHTML = formattedOutput['html'];
    if(formattedOutput['response'] === 'error')
        displayErrorNotification(formattedOutput['title'], formattedOutput['success']);
}
function calculateRation()
{
    var formattedOutput = Calculator.calculateRation();
    if(formattedOutput['html'])
        document.getElementById('calculatorUpdatePanel').innerHTML = formattedOutput['html'];
}
function getUserRations(rationsScheduledForDeletion)
{
    var formattedOutput = Calculator.getRations(rationsScheduledForDeletion);
    document.getElementById('calculatorUpdatePanel').innerHTML = formattedOutput['html'];
    $('#userCreatedRations').dataTable();
}
function getCommodities(deleteCommodities)
{
    var formattedOutput = Calculator.getCommodities(deleteCommodities);
    document.getElementById('calculatorUpdatePanel').innerHTML = formattedOutput['html'];
    $('#commoditiesTable').dataTable();
}
function getDetailedViewOfCommodity(commodityCode)
{
    var formattedOutput = Calculator.getDetailedViewOfCommodity(commodityCode);
    document.getElementById('calculatorUpdatePanel').innerHTML = formattedOutput['html'];
}
function deleteConfirmationOfCommodity(commodityTitle)
{
    var formattedOutput = Calculator.deleteConfirmationOfCommodity(commodityTitle);
    document.getElementById('calculatorUpdatePanel').innerHTML = formattedOutput['html'];
}
function deleteCommodity(commodityCode)
{
    var formattedOutput = Calculator.deleteCommodity(commodityCode);
    document.getElementById('calculatorUpdatePanel').innerHTML = formattedOutput['html'];
}
function getDetailedViewOfRation(userRationsCode)
{
    var formattedOutput = Calculator.getDetailedViewOfRation(userRationsCode);
    document.getElementById('calculatorUpdatePanel').innerHTML = formattedOutput['html'];
    $('#detailedViewRationTable').dataTable();
}
function deleteConfirmationOfRation(userRationsCode)
{
    var formattedOutput = Calculator.deleteConfirmationOfRation(userRationsCode);
    document.getElementById('calculatorUpdatePanel').innerHTML = formattedOutput['html'];
    $('#deletedViewRationTable').dataTable();
}
function deleteRation(userRationsCode)
{
    var formattedOutput = Calculator.deleteRation(userRationsCode);
    document.getElementById('calculatorUpdatePanel').innerHTML = formattedOutput['html'];
    $('#userCreatedRations').dataTable();
}
function generatePDFFile()
{
    var formattedOutput = Calculator.generatePDFFile();
    document.getElementById('calculatorUpdatePanel').innerHTML = formattedOutput['html'];
}
function emailPDFFile()
{
    var formattedOutput = Calculator.emailPDFFile();
    document.getElementById('calculatorUpdatePanel').innerHTML = formattedOutput['html'];
}
function emailPDFFileInvoked()
{
    var emailAddressTextBox = document.getElementById('emailAddressTextBox').value;
    //$('#emailPDFFileUpdatePanel_Loader').show();
    //$('#emailPDFFileUpdatePanel_Content').hide();
    var formattedOutput = Calculator.emailPDFFileInvoked(emailAddressTextBox);
    document.getElementById('emailPDFFileUpdatePanel_Content').innerHTML = formattedOutput['html'];
    //$('#emailPDFFileUpdatePanel_Loader').hide();
    //$('#emailPDFFileUpdatePanel_Content').show();
}
function printPDFFile()
{
    var formattedOutput = Calculator.printPDFFile();
    document.getElementById('calculatorUpdatePanel').innerHTML = formattedOutput['html'];
}
function addACommodityMenu()
{
    var formattedOutput = Calculator.addACommodityMenu();
    document.getElementById('calculatorUpdatePanel').innerHTML = formattedOutput['html'];
}
function saveNewCommodity(textFieldIdentifier, numberOfTextFields)
{
    var parameters = [];
    for(var counter = 0; counter < numberOfTextFields; counter++)
        parameters[counter] = document.getElementById(textFieldIdentifier + '_' + counter).value;
    var formattedOutput = Calculator.saveNewCommodity(parameters);
    document.getElementById('calculatorUpdatePanel').innerHTML = formattedOutput['html'];
}
function createSettingsMenu()
{
    var formattedOutput = Calculator.createSettingsMenu('');
    document.getElementById('calculatorUpdatePanel').innerHTML = formattedOutput['html'];
}
function saveSettingsMenu()
{
    var numberOfPlaces = document.getElementById('numberOfPlacesTextBox').value;
    var currencySymbol = document.getElementById('currencySymbolTextBox').value;
    var formattedOutput = Calculator.saveSettingsMenu(numberOfPlaces, currencySymbol);
    document.getElementById('calculatorUpdatePanel').innerHTML = formattedOutput['html'];
}
function getMainMenu()
{
    var formattedOutput = Calculator.getMainMenu();
    document.getElementById('calculatorUpdatePanel').innerHTML = formattedOutput['html'];
}
function createDashboardPage()
{
    var formattedOutput = Dashboard.createDashboardPage();
    document.getElementById('mainUpdatePanel').innerHTML = formattedOutput['html'];
    createCommodityTiles('viewPricesInTabularFormat');
}
function generatePDFFileForCommodityPrices()
{
    var formattedOutput = Dashboard.generatePDFFile();
    document.getElementById('dashboardUpdatePanel').innerHTML = formattedOutput['html'];
}
function emailPDFFileForCommodityPrices()
{
    var formattedOutput = Dashboard.emailPDFFile();
    document.getElementById('dashboardUpdatePanel').innerHTML = formattedOutput['html'];
}
function emailPDFFileInvokedForCommodityPrices()
{
    var emailAddress = document.getElementById('emailAddressTextBox').value;
    var formattedOutput = Dashboard.emailPDFFileInvoked(emailAddress);
    document.getElementById('emailPDFFileUpdatePanel').innerHTML = formattedOutput['html'];
}
function printPDFFileForCommodityPrices()
{
    var formattedOutput = Dashboard.printPDFFile();
    document.getElementById('dashboardUpdatePanel').innerHTML = formattedOutput['html'];
}
function createMessagePage()
{
    var formattedOutput = Message.createMessagePage();
    document.getElementById('mainUpdatePanel').innerHTML = formattedOutput['html'];
}
function createMainMenuForMessagePage()
{
    var formattedOutput = Message.createMainMenu();
    document.getElementById('messagesUpdatePanel').innerHTML = formattedOutput['html'];
}
function viewReceivedMessages()
{
    var formattedOutput = Message.viewReceivedMessages();
    document.getElementById('messagesUpdatePanel').innerHTML = formattedOutput['html'];
}
function viewSentMessages()
{
    var formattedOutput = Message.viewSentMessages('');
    document.getElementById('messagesUpdatePanel').innerHTML = formattedOutput['html'];
}
function createNewMessage()
{
    var formattedOutput = Message.createNewMessage();
    document.getElementById('messagesUpdatePanel').innerHTML = formattedOutput['html'];
}
function saveNewMessage()
{
    var selectedUsername = getSelectedItemFromDropDown(0);
    var subject = document.getElementById('subject').value;
    var description = document.getElementById('description').value;
    var formattedOutput = Message.saveNewMessage(selectedUsername, subject, description);
    if(formattedOutput['html'])
        document.getElementById('messagesUpdatePanel').innerHTML = formattedOutput['html'];
    if(formattedOutput['response'] === 'error')
        displayErrorNotification(formattedOutput['title'], formattedOutput['content']);

}
function getSelectedItemFromDropDown(dropdownNumber)
{
    return document.getElementsByClassName("select2-selection__rendered")[dropdownNumber].innerText.substring(1);
}
function createMainMenu()
{
    var formattedOutput = Dashboard.createMainMenu();
    document.getElementById('dashboardUpdatePanel').innerHTML = formattedOutput['html'];
}
function createCommodityTiles(onClickEvent)
{
    var formattedOutput = Dashboard.createCommodityTiles(onClickEvent);
    document.getElementById('dashboardUpdatePanel').innerHTML = formattedOutput['html'];
}
function viewPricesInTabularFormat(commodityIdentifier)
{
    var formattedOutput = Dashboard.viewPricesInTabularFormat(commodityIdentifier);
    document.getElementById('dashboardUpdatePanel').innerHTML = formattedOutput['html'];
}
function viewPricesInBarChartFormat(commodityIdentifier)
{
    alert('VIEW PRICES IN BAR CHART FORMAT');
    var formattedOutput = Dashboard.viewPricesInBarChartFormat(commodityIdentifier);
    alert('FORMATTED OUTPUT: ' + formattedOutput['barChart']['labels']);

    document.getElementById('dashboardUpdatePanel').innerHTML = formattedOutput['html'];
    //if(formattedOutput['javascript'])
        //addFunctionToPage(formattedOutput['javascript']);
    //createBarChart();
    //createBarChartDynamically();
    alert('BEFORE CALLING CREATE BAR CHART FROM FILE');
    alert('EXISTING BAR CHART' + document.getElementById('barChart'));
    createBarChart('barChart', formattedOutput['barChart']['labels']);
}
function addFunctionToPage(functionContent)
{
    var newFunction = document.createElement('script');
    newFunction.text = functionContent;
    document.body.appendChild(newFunction);
}
function createBarChart(chartID, chartLabels)
{
    alert('CREATE BAR CHART FROM FILE');
    var barChart = document.getElementById(chartID);
    var ctx = document.getElementById(chartID).getContext('2d');
    alert('BAR CHART OBJECT: ' + ctx);
    var myChart = new Chart(ctx,
    {
        type: 'line',
        data:
        {
            labels: chartLabels,
            datasets:
            [{
                label: 'apples',
                data: [12, 19, 3, 17, 6, 3, 7],
                backgroundColor: "rgba(153, 255, 51, 0.4)"
            },
            {
                label: 'oranges',
                data: [2, 29, 5, 5, 2, 3, 10],
                backgroundColor: "rgba(255, 153, 0, 0.4)"
            }]
        }
    });
    alert('BAR CHART: ' + myChart);
}
function viewPricesInLineChartFormat(commodityIdentifier)
{
    var formattedOutput = Dashboard.viewPricesInLineChartFormat(commodityIdentifier);
    document.getElementById('dashboardUpdatePanel').innerHTML = formattedOutput['html'];
}
function getAdministrationMainMenu()
{
    var formattedOutput = Administration.createMainMenu();
    document.getElementById('administrationPanel').innerHTML = formattedOutput['html'];
}
function getTableContents(tableName)
{
    var formattedOutput = Administration.createTable(tableName);
    document.getElementById('administrationPanel').innerHTML = formattedOutput['html'];
}
function addNewRowToTable(tableName)
{
    var formattedOutput = Administration.createNewRow(tableName);
    document.getElementById('administrationPanel').innerHTML = formattedOutput['html'];
}
function saveNewRow(tableName, selectedInputs)
{
    var parameters = [];
    for(var counter = 0; counter < selectedInputs.length; counter++)
        parameters[counter] = document.getElementById(selectedInputs[counter]).value;
    var formattedOutput = Administration.saveNewRow(tableName, parameters);
    document.getElementById('administrationPanel').innerHTML = formattedOutput['html'];
}
function deleteExistingRow(tableName)
{
    var formattedOutput = Administration.createTableForDeletions(tableName);
    document.getElementById('administrationPanel').innerHTML = formattedOutput['html'];
}
function deleteSelectedRow(tableName, identifier)
{
    var formattedOutput = Administration.deleteSelectedRow(tableName, identifier);
    document.getElementById('administrationPanel').innerHTML = formattedOutput['html'];
}
function deleteSelectedRowConfirmation(tableName, identifier)
{
    var formattedOutput = Administration.deleteSelectedRowConfirmation(tableName, identifier);
    document.getElementById('administrationPanel').innerHTML = formattedOutput['html'];
}
function updateRows(tableName)
{
    var formattedOutput = Administration.createTableForUpdatingRows(tableName);
    document.getElementById('administrationPanel').innerHTML = formattedOutput['html'];
}
function updateExistingRow(tableName, identifier)
{
    var formattedOutput = Administration.updateExistingRow(tableName, identifier);
    document.getElementById('administrationPanel').innerHTML = formattedOutput['html'];
}
function updateExistingRowConfirmation(tableName, tableValues, identifier)
{
    var formattedOutput = Administration.updateExistingRow(tableName, tableValues, identifier);
    document.getElementById('administrationPanel').innerHTML = formattedOutput['html'];
}
function createEmailSettings()
{
    var formattedOutput = Administration.createEmailSettings('');
    document.getElementById('administrationPanel').innerHTML = formattedOutput['html'];
}
function saveEmailSettings()
{
    var mailUsername = document.getElementById('mailUsername').value;
    var mailPassword = document.getElementById('mailPassword').value;
    var mailSmtpAuth = document.getElementById('mailSmtpAuth').value;
    var mailSmtpStartTlsEnable = document.getElementById('mailSmtpStartTlsEnable').value;
    var mailSmtpHost = document.getElementById('mailSmtpHost').value;
    var mailSmtpPort = document.getElementById('mailSmtpPort').value;
    var formattedOutput = Administration.saveEmailSettings(mailUsername, mailPassword, mailSmtpAuth, mailSmtpStartTlsEnable, mailSmtpHost, mailSmtpPort);
    document.getElementById('administrationPanel').innerHTML = formattedOutput['html'];
}
function createSerialPortSettings()
{
    var formattedOutput = Administration.createSerialPortSettings('');
    document.getElementById('administrationPanel').innerHTML = formattedOutput['html'];
}
function saveSerialPortSettings()
{
    var portName = document.getElementById('portName').value;
    var baudRate = document.getElementById('baudRate').value;
    var dataBits = document.getElementById('dataBits').value;
    var stopBits = document.getElementById('stopBits').value;
    var parity = document.getElementById('parity').value;
    var formattedOutput = Administration.saveSerialPortSettings(portName, baudRate, dataBits, stopBits, parity);
    document.getElementById('administrationPanel').innerHTML = formattedOutput['html'];
}
function clearPanel(id)
{
    document.getElementById(id).innerHTML = "";
}
function formatValue(value)
{
    var $value = $('<ul class="sidebar navy" style="width:100%;"><li><a href="#"><span class="mif-search icon"></span><span class="title">' +
    value.text + '</span><span class="counter"></span></span></a></li></ul>');
    return $value;
}
function createFluentMenu(selectedItem)
{
    var formattedOutput = Administration.createFluentMenu(selectedItem);
    alert('FORMATTED OUTPUT: ' + formattedOutput['html']);
    document.getElementById('administrationFluentMenuUpdatePanel').innerHTML = formattedOutput['html'];
}
//function displayPanel(selectedItem)
//{
    //alert('PANEL ID: ' + id);
    //alert('SELECTED ITEM: ' + selectedItem);
    //alert('PANEL CHILDREN COUNT: ' + document.getElementById(id).children.length);
    /*var tabPanels = document.getElementsByClassName("tab-panel");
    alert('NUMBER OF TAB PANELS: ' + tabPanels.length);
    alert('SELECTED ITEM: ' + selectedItem);
    var tabPanelsIDs = [];
    for(var counter = 0; counter < tabPanels.length; counter++)
    {
        tabPanels[counter].style = "display: none;";
        tabPanelsIDs[counter] = tabPanels[counter].id;
    }
    tabPanels[selectedItem].style = "display: block;";
    $('#' + tabPanelsIDs[selectedItem]).show();
    alert('SELECTED ITEM HTML: ' + tabPanels[selectedItem].outerHTML);*/
    //var children = document.getElementById(id);
    /*var masterPanel = document.getElementById(id);
    alert('CHILDREN INNER HTML: ' + masterPanel.innerHTML);
    for(var counter = 0; counter < masterPanel.children.length; counter++)
    {
        alert('CHILDREN COUNTER CLASS NAME: ' + masterPanel.children[counter].className);
        alert('SELECTED CHILD INNER HTML: ' + masterPanel.children[counter].innerHTML);
        masterPanel.children[counter].style = "display: none;";
    }
    masterPanel.children[selectedItem].style = "display: block;";*/
    /*alert('DISPLAY PANEL FUNCTION CALLED');
    if(document.getElementById('administrationFluentMenu'))
    {
        alert('ADMINISTRATION FLUENT MENU PANEL LOCATED');
        var formattedItem = Administration.createMenuGroupBasedOnSelectedInteger(selectedItem);
        alert('FORMATTED HTML: ' + formattedItem);
        document.getElementById('administrationFluentMenu_0').innerHTML = formattedItem['html'];
        document.getElementById('administrationFluentMenu_0').style = "display:block;";
        document.getElementById('administrationFluentMenu_1').innerHTML = formattedItem['html'];
        document.getElementById('administrationFluentMenu_1').style = "display:block;";
    }*/
    //id="" + id + "_" + currentTabPosition++ + "\">");
//}
function createBarChartDynamically()
{
    alert('CREATE BAR CHART FUNCTION CALLED');
    var barChartContext = document.getElementById('barChart').getContext('2d');
    alert('BAR CHART OBJECT: ' + barChartContext);
    var myChart = new Chart(barChartContext,
        {
            type: 'line',
            data:{  labels: ['May \'17', 'Jul \'17', 'Nov \'17', 'Jan \'18', 'Mar \'18', 'May \'18', 'Jul \'18', 'Nov \'18', 'Jan \'19', 'Mar \'19'],
                    datasets:
                        [{  label: 'Spot Prices',
                            data: [147.0, 148.4, 138.0, 139.75, 142.05, 143.05, 145.0, 140.75, 142.3, 143.85]}]}});
    alert('BAR CHART: ' + myChart);
}